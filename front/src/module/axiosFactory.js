import axios from "axios";
import userStore from "@/state/userStore.js";

const SERVER_URL = import.meta.env.VITE_API_URL;

const instance = axios.create({
    baseURL: SERVER_URL,
    withCredentials: true,
    headers: {
        'Content-Type': 'application/json',
        ...(userStore.state.accessToken && { 'Authorization': 'Bearer ' + userStore.state.accessToken })
    }
});

instance.interceptors.request.use((config) => {
    const token = userStore.state.accessToken;
    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});

instance.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        if (error.response && error.response.status === 401) {
            instance.get("/logout")
                .then(_ => {
                    window.location.href = '/login';
                })
        }

        return Promise.reject(error);
    }
);

export default instance;
