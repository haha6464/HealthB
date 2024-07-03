import http from "@/utils/httpRequest";

export function getCityList(){
    return http({
        url: "/city/getCityList",
        method: "get",
    })
}