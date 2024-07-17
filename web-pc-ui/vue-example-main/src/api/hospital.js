import http from "@/utils/httpRequest";

export function findByOne(data){
    return http({
        url: "/hospital/adminGetHospitalListOne",
        method: "get",
        params: data
    })
}


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
/**
 * 修改数据
 * @param {*} data 
 * @returns 
 */
export function editHospital(data){
    return http({
        url: "/hospital/edit",
        method: "post",
        params: data
    })
}