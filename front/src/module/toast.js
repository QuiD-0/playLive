import {toast} from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

const successToast = (message) => {
    toastPlate(message, "success");
}

const errorToast = (message) => {
    toastPlate(message, "error");
}

const toastPlate = (message, type) => {
    toast(message, {
        "theme": "dark",
        "type": type,
        "position": "bottom-right",
        "closeOnClick": false,
        "autoClose": 2000,
        "hideProgressBar": true,
        "dangerouslyHTMLString": true
    });
}


export {successToast, errorToast};
