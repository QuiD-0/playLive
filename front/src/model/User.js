class User {
    constructor(username, password) {
        this.username = username;
        this.password = password;
    }
    message = ''

    validate() {
        if (this.username === '') {
            this.message = "아이디를 입력해주세요.";
            return false
        }
        if (this.password === '') {
            this.message = "비밀번호를 입력해주세요.";
            return false
        }
        return true;
    }
}

export default User
