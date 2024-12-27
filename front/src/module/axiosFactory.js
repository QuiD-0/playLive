import axios from "axios";
import userStore from "@/state/userStore.js";

const SERVER_URL = import.meta.env.VITE_API_URL;

const instance = axios.create({
    baseURL: SERVER_URL,
    withCredentials: true,
    headers: {
        'Content-Type': 'application/json'
    }
});

const authInstance = axios.create({
    baseURL: SERVER_URL,
    withCredentials: true,
    headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + userStore.state.accessToken
    }
});

authInstance.interceptors.request.use((config) => {
    const token = userStore.state.accessToken;
    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});

authInstance.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        if (error.response && error.response.status === 401) {
            userStore.commit('logout');
            window.location.href = '/';
        }

        return Promise.reject(error);
    }
);

export { instance, authInstance };
