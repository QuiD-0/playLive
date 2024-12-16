import User from "@/model/User.js";
import instance from "@/module/axiosFactory.js";
import userStore from "@/state/userStore.js";
import {errorToast} from "@/module/toast.js";

function login(id, password) {
    let request = new User(id, password);
    if (!request.validate()) {
        errorToast(request.message);
        return;
    }
    instance.post('/api/member/login', request)
        .then(response => {
            userStore.commit('setAccessToken', response.data.message.accessToken);
            userStore.commit('setRefreshToken', response.data.message.refreshToken);
            getUserInfo();
        })
        .catch(error => {
            errorToast(error.response.data.message);
        });
}

function getUserInfo() {
    instance.get('/api/member/me')
        .then(response => {
            userStore.commit('setUser', response.data.message);
        })
        .catch(error => {
            console.log(error);
        });
}

export {login, getUserInfo};
