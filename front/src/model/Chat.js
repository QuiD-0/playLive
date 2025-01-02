class Chat {
    constructor(
        chatroomId,
        memberId,
        message,
        type,
        nickname
    ) {
        this.chatroomId = chatroomId;
        this.memberId = memberId;
        this.message = message;
        this.type = type;
        this.nickname = nickname;
    }
}

export default Chat
