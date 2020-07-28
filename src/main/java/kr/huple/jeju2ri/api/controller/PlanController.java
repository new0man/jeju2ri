package kr.huple.jeju2ri.api.controller;

import kr.huple.jeju2ri.api.model.*;
import kr.huple.jeju2ri.api.model.response.*;
import kr.huple.jeju2ri.api.service.*;
import kr.huple.jeju2ri.configuration.response.RestResponse;
import kr.huple.jeju2ri.util.Word;
import org.springframework.http.HttpStatus;
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
    private final PlaceService placeService;
    private final FileUploadController fileUpload;

    public PlanController(PlanService planService
            , PlanDayService planDayService
            , PlanDayScheduleService planDayScheduleService
            , PlanMemberService planMemberService
            , SpotService spotService
            , PlaceService placeService
            , FileUploadController fileUpload) {
        this.planService = planService;
        this.planDayService = planDayService;
        this.planDayScheduleService = planDayScheduleService;
        this.planMemberService = planMemberService;
        this.spotService = spotService;
        this.placeService = placeService;
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
    @GetMapping(value = "/my-plans/{customerId}")
    public Object getMyPlans(@PathVariable("customerId") String customerId) throws Exception {
        return planService.findByCustomerId(customerId);
    }

    /**
     * 
     * @param customerId
     * @return
     * @throws Exception
     */
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
        // 일정 조회
        PlanMainResponse planMain = getPlan(planId);
        return planMain;

    }

    /**
     * 일정 정보
     * @param planId
     * @return
     * @throws Exception
     */
    public PlanMainResponse getPlan(String planId) throws Exception {
        PlanMainResponse planMain = new PlanMainResponse();

        // 일정 정보
        PlanResponse plan = planService.findByPlanId(planId);
        // 일정 상세 정보
        List<PlanDayResponse> planDays = planDayService.findByPlanId(planId);
        for(int i = 0; i < planDays.size(); i++) {
            PlanDayScheduleDto param = new PlanDayScheduleDto();
            param.setPlanId(planId);
            param.setPlanSeqNo(planDays.get(i).getPlanSeqNo());
            List<PlanDayScheduleResponse> planDaySchedules = planDayScheduleService.findByPk(param);
            for(int k = 0; k < planDaySchedules.size(); k++) {
                List<PlaceCategoryResponse> placeCategories = placeService.getPlaceCategory(planDaySchedules.get(k).getPlaceId());
                planDaySchedules.get(k).setCategories(placeCategories);
            }
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
    @GetMapping(value = "/{planId}/days/{planSeqNo}/schedules")
    public Object getSchedule(@PathVariable("planId") String planId
                              , @PathVariable("planSeqNo") Integer planSeqNo) throws Exception {
        PlanDayScheduleDto param = new PlanDayScheduleDto();
        param.setPlanId(planId);
        param.setPlanSeqNo(planSeqNo);
        List<PlanDayScheduleResponse> planDaySchedules = planDayScheduleService.findByPk(param);
        return planDaySchedules;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Object add(@RequestBody PlanDto paramPlan) throws Exception {
        String planId = planService.getPlanId();
        paramPlan.setPlanId(planId);

        // 일정 등록 START
        planService.add(paramPlan);

        PlanDayDto paramPlanDay = null;
        LocalDate startDate = LocalDate.parse(paramPlan.getPlanStartDate());
        LocalDate endDate = LocalDate.parse(paramPlan.getPlanEndDate());
        for(LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            paramPlanDay = new PlanDayDto();
            paramPlanDay.setPlanId(planId);
            paramPlanDay.setPlanDate(date.toString());
            planDayService.add(paramPlanDay);
        }

        PlanMemberDto paramPlanMember = new PlanMemberDto();
        paramPlanMember.setPlanId(planId);
        paramPlanMember.setCustomerId(paramPlan.getCreateId());
        paramPlanMember.setAuth("L");
        planMemberService.add(paramPlanMember);
        // 일정 등록 END

        // 일정 조회
        PlanMainResponse planMain = getPlan(planId);

        return planMain;
    }

    /**
     * 일정 타이틀 변경
     * @param planId
     * @param planDto
     * @return
     * @throws Exception
     */
    @PatchMapping(value = "/{planId}/title", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object editTitle(@PathVariable("planId") String planId
                       , @RequestBody PlanDto planDto) throws Exception {
        planDto.setPlanId(planId);
        planService.editTitle(planDto);
        return null;
    }

    /**
     * 일정 이미지 변경
     * @param planId
     * @param planDto
     * @return
     * @throws Exception
     */
    @PatchMapping(value = "/{planId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object editImage(@PathVariable("planId") String planId
            , PlanDto planDto) throws Exception {

        FileDto file = new FileDto();
        file.setPhoto(planDto.getImage());
        RestResponse<?> restResponse = (RestResponse<?>) fileUpload.fileUpload(file);

        planDto.setPlanId(planId);
        planDto.setRpsntImageUrl(restResponse.getResult().toString());
        planService.editImage(planDto);

        return null;

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
    @DeleteMapping(value = "/{planId}/days/{planSeqNo}/schedules/{scheduleSeqNo}")
    public Object deleteSchedule(@PathVariable("planId") String planId
                                 , @PathVariable("planSeqNo") Integer planSeqNo
                                 , @PathVariable("scheduleSeqNo") Integer scheduleSeqNo) throws Exception {
        PlanDayScheduleDto param = new PlanDayScheduleDto();
        param.setPlanId(planId);
        param.setPlanSeqNo(planSeqNo);
        param.setScheduleSeqNo(scheduleSeqNo);
        planDayScheduleService.delete(param);
        return null;
    }

    /**
     * Schedule 추가 - PLACE, POST
     * @param planDaySchedules
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/{planId}/days/{planSeqNo}/schedules")
    @ResponseStatus(HttpStatus.CREATED)
    public Object addSchedule(@PathVariable("planId") String planId
                              , @PathVariable("planSeqNo") Integer planSeqNo
                              , @RequestBody List<PlanDayScheduleDto> planDaySchedules) throws Exception {
    System.out.println("----- addSchedule -----");
        for(int i = 0; i < planDaySchedules.size(); i++) {
            planDaySchedules.get(i).setPlanId(planId);
            planDaySchedules.get(i).setPlanSeqNo(planSeqNo);
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
    @PostMapping(value = "/{planId}/days/{planSeqNo}/spots", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Object addSpot(@PathVariable("planId") String planId
                          , @PathVariable("planSeqNo") Integer planSeqNo
                          , SpotDto spot) throws Exception {

        // SPOT 추가 Start
        String spotId = spotService.getSpotId();
        spot.setSpotId(spotId);
        FileDto fileDto = new FileDto();
        if(spot.getImage() != null &&
                spot.getImage().getOriginalFilename() != null &&
                !"".equals(spot.getImage().getOriginalFilename())) {
            fileDto.setPhoto(spot.getImage());
            RestResponse<?> restResponse = (RestResponse<?>) fileUpload.fileUpload(fileDto);
            spot.setImageUrl(restResponse.getResult().toString());
        }

        spotService.add(spot);
        // SPOT 추가 End

        // SPOT(출발장소, 도착장소, 개인장소) 일정 Schedule 추가 Start
        PlanDayScheduleDto planDaySchedule = new PlanDayScheduleDto();
        planDaySchedule.setPlanId(planId);
        planDaySchedule.setPlanSeqNo(planSeqNo);
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
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{planId}/members")
    public Object addMember(@PathVariable("planId") String planId
                            , @RequestBody PlanMemberDto planMember) throws Exception {

        planMember.setPlanId(planId);
        planMember.setAuth("M");
        planMemberService.add(planMember);
        return null;

    }

    /**
     * 일정 멤버 추방
     * @param planId
     * @param customerId
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/{planId}/members/{customerId}")
    public Object deleteMember(@PathVariable("planId") String planId
                               , @PathVariable("customerId") String customerId) throws Exception {
        PlanMemberDto planMember = new PlanMemberDto();
        planMember.setPlanId(planId);
        planMember.setCustomerId(customerId);
        planMemberService.delete(planMember);
        return null;
    }

    /**
     * 일정 리더 변경
     * @param planId
     * @param planMember
     * @return
     * @throws Exception
     */
    @PatchMapping(value = "/{planId}/members")
    public Object editMember(@PathVariable("planId") String planId
                             , @RequestBody PlanMemberDto planMember) throws Exception {
        PlanMemberDto param = new PlanMemberDto();
        param.setPlanId(planId);
        param.setAuth("M");
        param.setCustomerId(planMember.getBeforeLeaderCustomerId());
        planMemberService.edit(param);

        param = new PlanMemberDto();
        param.setPlanId(planId);
        param.setAuth("L");
        param.setCustomerId(planMember.getAfterLeaderCustomerId());
        planMemberService.edit(param);

        return null;
    }

    /**
     * 일정 스케쥴 이동 - 단건
     * @param planId
     * @param planSeqNo
     * @param scheduleSeqNo
     * @param planDaySchedule
     * @return
     * @throws Exception
     */
    @PatchMapping(value = "/{planId}/days/{planSeqNo}/schedules/{scheduleSeqNo}")
    public Object editSchedule(@PathVariable("planId") String planId
                               , @PathVariable("planSeqNo") Integer planSeqNo
                               , @PathVariable("scheduleSeqNo") Integer scheduleSeqNo
                               , @RequestBody PlanDayScheduleDto planDaySchedule) throws Exception {

        planDaySchedule.setPlanId(planId);
        planDaySchedule.setPlanSeqNo(planSeqNo);
        planDaySchedule.setScheduleSeqNo(scheduleSeqNo);

        planDayScheduleService.edit(planDaySchedule);
        planDayScheduleService.delete(planDaySchedule);

        return null;
    }

    /**
     * 정 스케쥴 이동 - Day
     * @param planId
     * @param planSeqNo
     * @param planDaySchedule
     * @return
     * @throws Exception
     */
    @PatchMapping(value = "/{planId}/days/{planSeqNo}/schedules")
    public Object editSchedules(@PathVariable("planId") String planId
            , @PathVariable("planSeqNo") Integer planSeqNo
            , @RequestBody PlanDayScheduleDto planDaySchedule) throws Exception {
        planDaySchedule.setPlanId(planId);
        planDaySchedule.setPlanSeqNo(planSeqNo);
        List<PlanDayScheduleDto> planDaySchedules = planDayScheduleService.findByPlanDay(planDaySchedule);
        for (int i = 0; i < planDaySchedules.size(); i++) {
            planDaySchedules.get(i).setAfterPlanId(planDaySchedule.getAfterPlanId());
            planDaySchedules.get(i).setAfterPlanSeqNo(planDaySchedule.getAfterPlanSeqNo());
            planDaySchedules.get(i).setAfterPlanDate(planDaySchedule.getAfterPlanDate());
            planDayScheduleService.edit(planDaySchedules.get(i));
            planDayScheduleService.delete(planDaySchedules.get(i));
        }
        return null;
    }

    /**
     * 일정 스케쥴 순서 변경
     * @param planId
     * @param planSeqNo
     * @param planDaySchedules
     * @return
     * @throws Exception
     */
    @PatchMapping(value = "/{planId}/days/{planSeqNo}")
    public Object editScheduleOrder(@PathVariable("planId") String planId
                                    , @PathVariable("planSeqNo") Integer planSeqNo
                                    , @RequestBody List<PlanDayScheduleDto> planDaySchedules) throws Exception {
        for(int i = 0; i < planDaySchedules.size(); i++) {
            planDaySchedules.get(i).setPlanId(planId);
            planDaySchedules.get(i).setPlanSeqNo(planSeqNo);
            planDayScheduleService.editOrder(planDaySchedules.get(i));
        }
        return Word.REGIST_SUCCESS_MSG;
    }

}
