function getAxios(url, params, callback) {
    return axios.get(url, {
        params: params
    })
        .then(function (result) {
            doCallback(result, callback);
        })
        .catch(function (reason) {
            responseCheck(reason);
        });
}

function postAxios(url, params, callback) {
    return axios.post(url, params)
        .then(function (result) {
            doCallback(result, callback);
        })
        .catch(function (reason) {
            responseCheck(reason);
        });
}

function doCallback(result, callback) {
    if (result.data.status === "SUCCESS") {
        callback(result.data.message);
    } else {
        throw new Error(result.data.message);
    }
}

function responseCheck(reason) {
    if (reason.status === 401) {
        alert("로그인이 필요합니다.");
    } else if (reason.status === 403) {
        alert("접근 권한이 없습니다.");
    } else {
        console.log(reason)
        alert(reason.response.data.message);
    }
}