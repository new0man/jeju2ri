package kr.huple.jeju2ri.api.controller;

import kr.huple.jeju2ri.api.model.*;
import kr.huple.jeju2ri.api.model.response.*;
import kr.huple.jeju2ri.api.service.*;
import kr.huple.jeju2ri.configuration.response.RestResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/plans")
public class PlanController {

    private final PlanService planService;
    private final PlanDayService planDayService;
    private final PlanDayScheduleService planDayScheduleService;
    private final PlanMemberService planMemberService;
    private final SpotService spotService;
    private final FileUploadController fileUpload;

    public PlanController(PlanService planService
            , PlanDayService planDayService
            , PlanDayScheduleService planDayScheduleService
            , PlanMemberService planMemberService
            , SpotService spotService
            , FileUploadController fileUpload) {
        this.planService = planService;
        this.planDayService = planDayService;
        this.planDayScheduleService = planDayScheduleService;
        this.planMemberService = planMemberService;
        this.spotService = spotService;
        this.fileUpload = fileUpload;
    }

    /**
     * 일정 멤버 목록 조회
     * @param planId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/{planId}/members")
    public Object getPlanMembers(@PathVariable("planId") String planId) throws Exception {
        return planMemberService.findByPlanId(planId);
    }

    /**
     * 내 일정 목록 조회
     * @param customerId
     * @return
     * @throws Exception
     */
    // todo 확인
    @GetMapping(value = "/my-plans/{customerId}")
    public Object getMyPlans(@PathVariable("customerId") String customerId) throws Exception {
        return planService.findByCustomerId(customerId);
    }

    @GetMapping(value = "/my-plans/{customerId}/size")
    public Object getMyPlansSize(@PathVariable("customerId") String customerId) throws Exception {
        return planService.findByCustomerId(customerId).size();
    }

    /**
     * 일정 조회
     * @param planId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/{planId}")
    public Object getPlanByPlanId(@PathVariable("planId") String planId) throws Exception {

        PlanMainResponse planMain = new PlanMainResponse();

        // 일정 정보
        PlanResponse plan = planService.findByPlanId(planId);
        // 일정 상세 정보
        List<PlanDayResponse> planDays = planDayService.findByPlanId(planId);
        for(int i = 0; i < planDays.size(); i++) {
            PlanDaySchedule param = new PlanDaySchedule();
            param.setPlanId(planId);
            param.setPlanSeqNo(planDays.get(i).getPlanSeqNo());
            List<PlanDayScheduleResponse> planDaySchedules = planDayScheduleService.findByPk(param);
            planDays.get(i).setPlanDaySchedules(planDaySchedules);
        }
        // 일정 멤버 정보
        List<PlanMemberResponse> planMembers = planMemberService.findByPlanId(planId);

        planMain.setPlan(plan);
        planMain.setPlanDays(planDays);
        planMain.setPlanMembers(planMembers);

        return planMain;

    }

    /**
     * Schedule 조회
     * @param planId
     * @param planSeqNo
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/{planId}/day/{planSeqNo}/schedules")
    public Object getSchedule(@PathVariable("planId") String planId
                              , @PathVariable("planSeqNo") Integer planSeqNo) throws Exception {
        PlanDaySchedule param = new PlanDaySchedule();
        param.setPlanId(planId);
        param.setPlanSeqNo(planSeqNo);
        List<PlanDayScheduleResponse> planDaySchedules = planDayScheduleService.findByPk(param);
        return planDaySchedules;
    }

    /**
     * 일정 삭제
     * @param planId
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/{planId}")
    public Object delete(@PathVariable("planId") String planId) throws Exception {
        planService.delete(planId);
        return null;
    }

    /**
     * Schedule 삭제
     * @param planId
     * @param planSeqNo
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/{planId}/day/{planSeqNo}/schedules")
    public Object deleteSchedule(@PathVariable("planId") String planId
            , @PathVariable("planSeqNo") Integer planSeqNo) throws Exception {
        PlanDaySchedule param = new PlanDaySchedule();
        param.setPlanId(planId);
        param.setPlanSeqNo(planSeqNo);
        planDayScheduleService.delete(param);
        return null;
    }

    /**
     * 일정 생성
     * @param paramPlan
     * @return
     * @throws Exception
     */
    @PostMapping(value = ""
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public Object add(@RequestBody Plan paramPlan) throws Exception {
        String planId = planService.getPlanId();
        paramPlan.setPlanId(planId);

        // 일정 등록 START
        planService.add(paramPlan);

        PlanDay paramPlanDay = null;
        LocalDate startDate = LocalDate.parse(paramPlan.getPlanStartDate());
        LocalDate endDate = LocalDate.parse(paramPlan.getPlanEndDate());
        for(LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            paramPlanDay = new PlanDay();
            paramPlanDay.setPlanId(planId);
            paramPlanDay.setPlanDate(date.toString());
            planDayService.add(paramPlanDay);
        }

        PlanMember paramPlanMember = new PlanMember();
        paramPlanMember.setPlanId(planId);
        paramPlanMember.setCustomerId(paramPlan.getCreateId());
        paramPlanMember.setAuth("L");
        planMemberService.add(paramPlanMember);
        // 일정 등록 END

        // 일정 조회
        PlanMainResponse planMain = new PlanMainResponse();

        // 일정 정보
        PlanResponse plan = planService.findByPlanId(planId);
        // 일정 상세 정보
        List<PlanDayResponse> planDays = planDayService.findByPlanId(planId);
        for(int i = 0; i < planDays.size(); i++) {
            PlanDaySchedule paramSchedule = new PlanDaySchedule();
            paramSchedule.setPlanId(planId);
            paramSchedule.setPlanSeqNo(planDays.get(i).getPlanSeqNo());
            List<PlanDayScheduleResponse> planDaySchedules = planDayScheduleService.findByPk(paramSchedule);
            planDays.get(i).setPlanDaySchedules(planDaySchedules);
        }
        // 일정 멤버 정보
        List<PlanMemberResponse> planMembers = planMemberService.findByPlanId(planId);

        planMain.setPlan(plan);
        planMain.setPlanDays(planDays);
        planMain.setPlanMembers(planMembers);

        return planMain;
    }

    /**
     * Schedule 추가 - PLACE, POST
     * @param planDaySchedules
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/{planId}/schedules/{planSeqNo}")
    public Object addSchedule(@RequestBody List<PlanDaySchedule> planDaySchedules) throws Exception {

        for(int i = 0; i < planDaySchedules.size(); i++) {
            planDayScheduleService.add(planDaySchedules.get(i));
        }

        return null;
    }

    /**
     * Schedule 추가 - SPOT(출발장소, 도착장소, 개인장소)
     * @param spot
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/{planId}/schedules/{planSeqNo}/spots")
    public Object addSpot(@RequestBody Spot spot) throws Exception {

        // SPOT 추가 Start
        String spotId = spotService.getSpotId();
        spot.setSpotId(spotId);
        FileDto fileDto = new FileDto();
        if(spot.getPhoto() != null &&
                spot.getPhoto().getOriginalFilename() != null &&
                !"".equals(spot.getPhoto().getOriginalFilename())) {
            fileDto.setPhoto(spot.getPhoto());
            RestResponse<?> restResponse = (RestResponse<?>) fileUpload.fileUpload(fileDto);
            spot.setImageUrl(restResponse.getResult().toString());
        }

        spotService.add(spot);
        // SPOT 추가 End

        // SPOT(출발장소, 도착장소, 개인장소) 일정 Schedule 추가 Start
        PlanDaySchedule planDaySchedule = new PlanDaySchedule();
        planDaySchedule.setPlanId(spot.getPlanId());
        planDaySchedule.setPlanSeqNo(spot.getPlanSeqNo());
        planDaySchedule.setPlanDate(spot.getPlanDate());
        planDaySchedule.setGubun(spot.getGubun());
        planDaySchedule.setSpotId(spotId);

        planDayScheduleService.add(planDaySchedule);
        // SPOT(출발장소, 도착장소, 개인장소) 일정 Schedule 추가 End

        return null;

    }

    /**
     * 일정 멤버 추가
     * @param planMember
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/{planId}/members")
    public Object addMember(@RequestBody PlanMember planMember) throws Exception {

        planMember.setAuth("M");
        planMemberService.add(planMember);
        return null;

    }

    /**
     * 일정 멤버 추방
     * @param planMember
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/{planId}/members")
    public Object deleteMember(@RequestBody PlanMember planMember) throws Exception {
        planMemberService.delete(planMember);
        return null;
    }

    /**
     * 일정 리더 변경
     * @param planMember
     * @return
     * @throws Exception
     */
    @PatchMapping(value = "/{planId}/members")
    public Object editMember(@RequestBody PlanMember planMember) throws Exception {
        PlanMember param = new PlanMember();
        param.setPlanId(planMember.getPlanId());
        param.setAuth("M");
        param.setCustomerId(planMember.getBeforeLeaderCustomerId());
        planMemberService.edit(param);

        param = new PlanMember();
        param.setPlanId(planMember.getPlanId());
        param.setAuth("L");
        param.setCustomerId(planMember.getAfterLeaderCustomerId());
        planMemberService.edit(param);

        return null;
    }

    /**
     * 일정 스케쥴 변경
     * @param planDaySchedule
     * @return
     * @throws Exception
     */
    @PatchMapping(value = "/{planId}/day/{planSeqNo}/schedules/{scheduleSeqNo}")
    public Object editSchedule(@RequestBody PlanDaySchedule planDaySchedule) throws Exception {
        return null;
    }

    /**
     * 일정 스케쥴 순서 변경
     * @param planDaySchedules
     * @return
     * @throws Exception
     */
    @PatchMapping(value = "/{planId}/day/{planSeqNo}")
    public Object editScheduleOrder(@RequestBody List<PlanDaySchedule> planDaySchedules) throws Exception {
        return null;
    }

}
