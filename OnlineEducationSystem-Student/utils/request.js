import axios from "axios";
import cookie from "js-cookie";
import { Message } from 'element-ui'

// 创建axios实例
const service = axios.create({
    baseURL: "http://localhost:8500",
    timeout: 20000,
});

// http request拦截器，判断cookie中是否有token字符串，如果有，则将token存入请求头中
service.interceptors.request.use(
    (config) => {
        if (cookie.get("oes_token")) {
            config.headers["token"] = cookie.get("oes_token");
        }
        return config;
    },
    (err) => {
        return Promise.reject(err);
    }
);

// http response拦截器,仿照admin框架的request.js
service.interceptors.response.use((response) => {
    if (response.data.code == 28000) {
        Message({
            message: "登录状态异常,请重新登录",
            type: "error",
            duration: 5 * 1000,
        })
        window.location.href = "/login";
        return;
    } else {
        if (response.data.code !== 20000) {
            //25000,订单支付中，不做任何提示
            if (response.data.code !== 25000) {
                Message({
                    message: response.data.message || "error",
                    type: "error",
                    duration: 5 * 1000,
                });
            }
        } else {
            return response.data;
        }
        return Promise.reject(new Error(response.data.message || "error"))
    }
});

export default service;
