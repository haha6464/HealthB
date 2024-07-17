import http from "@/utils/httpRequest";

export function getData(data) {
    return http({
        url: "/patientescortlist/adminFindEscort",
        method: "get",
        params: data
    })
}