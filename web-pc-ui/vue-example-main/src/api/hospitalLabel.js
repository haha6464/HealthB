import http from "@/utils/httpRequest";
 
/**
 * 添加数据
 * @param {*} data 
 * @returns 
 */
export function labeladd(data){
    return http({
        url: "/hospitalLabel/add",
        method: "post",
        params: data
    })
}

 