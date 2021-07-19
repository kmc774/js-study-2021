const Singleton = (function () {
    let instance;

    function sample() {
        return {
            //public 메서드 정의
            publicMethod: function() {
                return `hello Singleton Pattern`;
            },
            //public 속성 정의
            publicProp: `single value`
        }
    }

    // public 메서드인 getinstance 를 정의한 객체
    //이 메서드를 통해 비공개된 메서드와 변수에 접근 가능
    return {
        getInstance: function() {
            if(!instance) {
                instance = sample();
            }
            return instance;
        }
    }
})();

//singleton object를 생성
let a = new Array();
//singleton object를 생성
let b = new Array();

console.log(a === b);
console.log(a.publicMethod());
console.log(b.publicMethod());