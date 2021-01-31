import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

//@Component
public class GrayFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE ;
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        String uid = request.getParameter("uid");

        // 有可能是空
        if(!StringUtils.isEmpty(uid)){
            long l = NumberUtils.toLong(uid);
            if(l>0){
//                // 通过奇数或者偶数区分
//                if(l%2==0){
//                    RibbonFilterContextHolder.getCurrentContext().add("server_version","1");
//                }else{
//                    RibbonFilterContextHolder.getCurrentContext().add("server_version","2");
//                }
            }
        }

        return null;
    }
}
