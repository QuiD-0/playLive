import axios from "axios";

const SERVER_URL = import.meta.env.VITE_SERVER_URL;
const instance = axios.create({
    baseURL: SERVER_URL,
    timeout: 3000,
    // headers: {'Authorization': 'Bearer ' + }
});

export function getAxios(url, data, callback) {
    instance.get(url, data)
        .then(function (response) {
            callback(response.data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

export function postAxios(url, data, callback) {
    instance.post(url, data)
        .then(function (response) {
            callback(response.data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

export function putAxios(url, data, callback) {
    instance.put(url, data)
        .then(function (response) {
            callback(response.data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

export function deleteAxios(url, data, callback) {
    instance.delete(url, data)
        .then(function (response) {
            callback(response.data);
        })
        .catch(function (error) {
            console.log(error);
        });
}
