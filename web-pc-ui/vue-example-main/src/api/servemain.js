import http from "@/utils/httpRequest";

export function getData(data){
    return http({
        url: "/serve/adminGetServeList",
        method: "GET",
        params: data
    })
}