// https://nowonbun.tistory.com/467
// https://flowarc.tistory.com/entry/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8Javascript-%EB%94%94%EC%9E%90%EC%9D%B8-%ED%8C%A8%ED%84%B4-%EC%A4%91%EC%9E%AC%EC%9E%90Mediator-%ED%8C%A8%ED%84%B4
// https://roseline.oopy.io/dev/gof-design-pattern-mediator

//참여자 클래스
class Participant {
    constructor(name) {
        this.name = name
        this.chatRoom = null
        this.messages = []
    }

    send(message, to) {
        this.chatRoom.send(message, this, to)
    }

    receive(message, from) {
        this.messages.push({ message, from })
    }

    showMessages() {
        console.log(this.messages)
    }
}

//ChatRoom 클래스 (중재자클래스)
class ChatRoom {
    constructor() {
        this.participants = {}
    }

    enter(participant) {
        this.participants[participant.name] = participant
        participant.chatRoom = this
    }

    send(message, participant, to) {
        this.participants[to.name].receive(message, participant)
    }
}

const chatRoom = new ChatRoom()

const user1 = new Participant('user1')
const user2 = new Participant('user2')
const user3 = new Participant('user3')

//채팅방에 참여자가 입장
chatRoom.enter(user1)
chatRoom.enter(user2)
chatRoom.enter(user3)

//대화
user1.send('Hello', user2)
user2.send('Nice meet to you', user1)
user3.send('Boring....', user1)

user1.showMessages()
user2.showMessages()
user3.showMessages()

/* Result
[
  {
    message: 'Nice meet to you',
    from: Participant {
      name: 'user2',
      chatRoom: [ChatRoom],
      messages: [Array]
    }
  },
  {
    message: 'Boring....',
    from: Participant { name: 'user3', chatRoom: [ChatRoom], messages: [] }
  }
]
[
  {
    message: 'Hello',
    from: Participant {
      name: 'user1',
      chatRoom: [ChatRoom],
      messages: [Array]
    }
  }
]
[]
*/

