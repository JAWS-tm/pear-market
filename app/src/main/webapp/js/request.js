
function sendPost(url, paramData, successCallbackFn, failCallbackFn) {
    fetch(ctx + url, {
        method: "POST",headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(paramData)
    })
        .then(res => {
            res.text().then((data) => {
                if (res.status === 200) {
                    successCallbackFn(data);
                } else {
                    failCallbackFn(data);
                }
            })
        })
}