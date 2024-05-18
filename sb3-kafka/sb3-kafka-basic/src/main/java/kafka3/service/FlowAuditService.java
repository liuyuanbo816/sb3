package kafka3.service;

import org.springframework.stereotype.Service;

@Service
public class FlowAuditService {

//    public void t1(){
//        // 开启审批流程
//        String downloadFlow = staticDataService.getDcSystemParamByCache("APPSQL_DOWNLOAD_FLOW", "singleAudit");
//        // 封装接口入参
//        FlowParams flowParams = new FlowParams();
//        // 数据开放配置
//        flowParams.setFlowType(downloadFlow);
//        flowParams.setApplyName(MapUtils.getString(params, "applyName"));
//        // 查看详情
//        flowParams.setPageUrl(
//                "/ext/smart/datasetList/downloadApply?applyId=" + objApplyInfo.getApplyId() + "&viewType=popupView");
//        flowParams.setReceiveUrl("/smartservice/DatasetController/handleDownloadAfterAudit");
//        flowParams.setApplyReason(MapUtils.getString(params, "applyReason"));
//
//        flowParams.setSrcSystem(KeyValues.SERVICE_CODE);
//        flowParams.setServiceType(Constants.SERVICE_TYPE_CONSUME_DATASET);
//        Result result = dataMarketService.startAuditFlow(flowParams);
//        if (!result.isSuccess()) {
//            return ResponseUtil.fail(result.getResultDesc());
//        }
//
//        // 提交审批申请后保存审批工单号并修改取数应用状态
//        Long orderNo = Long.parseLong(result.getData().toString());
//        objApplyInfo.setOrderNo(orderNo);
//    }

}
