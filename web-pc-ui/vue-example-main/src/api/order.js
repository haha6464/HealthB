import http from "@/utils/httpRequest";

export function getData(data){
    return http({
        url: "/orderList/adminGetListData",
        method: "post",
        params: data
    })
}