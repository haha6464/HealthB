import http from "@/utils/httpRequest";

export function getHospitalList(data){
    return http({
        url: "/hospital/adminGetHospitalList",
        method: "get",
        params: data
    })
}

/**
 * 添加数据
 * @param {*} data 
 * @returns 
 */
export function add(data){
    return http({
        url: "/hospital/add",
        method: "post",
        params: data
    })
}