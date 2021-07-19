/*
* 전략패턴
* 전략 설정하는 부분 따로, 실행하는 부분 따로여서 전략을 설정해두면 실행하기 전에
* 자유롭게 전략을 바꿀 수 있음 실행한 후에도 바꿀 수 있음
* */

const Strategy = (function() {
    function Strategy() {
        this.strategy = null;
    }
    Strategy.prototype.setStrategy = function(strategy) {
        this.strategy = strategy;
    };
    Strategy.prototype.execute = function() {
        this.strategy.execute();
    };
    return Strategy;
})();

const ShipStrategy = (function() {
    function ShipStrategy() {}
    ShipStrategy.prototype.execute = function() {
        console.log('배로 이탈리아에 갑니다');
    };
    return ShipStrategy;
})();

const LandStrategy = (function() {
    function LandStrategy() {}
    LandStrategy.prototype.execute = function() {
        console.log('육로로 이탈리아에 갑니다');
    };
    return LandStrategy;
})();

const strat = new Strategy();
const ship = new ShipStrategy();
const land = new LandStrategy();
strat.setStrategy(ship);
strat.setStrategy(land); // 전략을 바꿈
strat.execute(); // 어떤 전략이든 설정된 것을 실행
// 육로로 이탈리아에 갑니다.