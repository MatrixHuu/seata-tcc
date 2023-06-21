package org.javaboy.common.feign;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xyma
 * @version 1.0
 * @data 2023/6/21 21:22
 */
@LocalTCC
public interface AccountServiceApi {

    @TwoPhaseBusinessAction(name = "accountServiceApi", commitMethod = "commit", rollbackMethod = "rollback")
    @RequestMapping("/account/deduct/prepare")
    boolean prepare(@RequestBody BusinessActionContext actionContext,
                    @RequestParam("userId") @BusinessActionContextParameter(paramName = "userId") String userId,
                    @RequestParam("money") @BusinessActionContextParameter(paramName = "money") Double money);

    @RequestMapping("/account/deduct/commit")
    boolean commit(@RequestBody BusinessActionContext businessActionContext);

    @RequestMapping("/account/deduct/rollback")
    boolean rollback(@RequestBody BusinessActionContext businessActionContext);

}
