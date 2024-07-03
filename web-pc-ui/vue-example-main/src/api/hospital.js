import http from "@/utils/httpRequest";

export function getHospitalList(data){
    return http({
        url: "/hospital/adminGetHospitalList",
        method: "get",
        params: data
    })
}