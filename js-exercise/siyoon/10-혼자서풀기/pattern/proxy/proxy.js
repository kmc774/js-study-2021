/*
* 프록시 패턴
* 프록시는 대리인이라는 뜻
* 사용자가 원하는 행동을 하기 전에 한 번 거쳐가는 단계
* 좋은 프록시는 사용자의 요청을 캐싱하여 성능을 높일 수도 있고 에러를 잡아낼 수도있지만
* 나쁜 프록시는 사용자의 요청을 왜곡하여 다른 동작을 하도록 만들 수도있음
* 프록시 패턴은 중간단계를 거쳐 원래 객체로 도달하게 만드는 패턴
* 중간단계에서 캐싱이나 에러처리를 할 수 있음
* */

const Praetorian = (function() {
    function Praetorian() {};
    Praetorian.prototype.report = function(fact) {
        console.log('황제에게 ' + fact + '을 보고드립니다!');
    };
    Praetorian.prototype.assassinate = function(target) {
        console.log(target + ' 암살 명령을 받았습니다');
    };
    return Praetorian;
})();

const PraetorianProxy = (function() {
    function PraetorianProxy(master) {
        this.master = master;
        this.praetorian = new Praetorian();
    }
    PraetorianProxy.prototype.report = function(fact) {
        const lie = '거짓';
        console.log(this.master + '에게 ' + fact + '을 보고드립니다');
        this.praetorian.report(lie);
    }
    PraetorianProxy.prototype.assassinate = function(target) {
        console.log('더 이상 ' + target + '을 암살하지 않습니다');
        this.praetorian.assassinate('Galba');
    }
    return PraetorianProxy;
})();

const praetorian = new PraetorianProxy('Otho');
praetorian.report('사실'); // Otho에게 사실을 보고드립니다. 황제에게 거짓을 보고드립니다.
praetorian.assassinate('Otho'); // 더 이상 Otho을 암살하지 않습니다. Galba 암살 명령을 받았습니다
