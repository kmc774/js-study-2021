
/*
* for문의 초기화문에서 흔히 사용되는 변수 i는 배열이 주어질 때 각각의 요소에 차례대로 접근하기 위해 사용
* 변수 i의 역할을 추상화 한 것이 iterator 패턴
* */

const Beehives = (function() {
    function Beehives(hiveList) {
        this.hiveList = hiveList;
        this.index = 0;
    }
    Beehives.prototype.next = function() {
        console.log(this.hiveList[this.index++] + '에서 꿀을 걷습니다');
    };
    Beehives.prototype.done = function() {
        return this.hiveList.length === this.index;
    };
    return Beehives;
})();

const beehives = new Beehives(['hive1', 'hive2', 'hive3', 'hive4', 'hive5', 'hive6', 'hive7', 'hive8', 'hive9']);
beehives.next(); // hive1에서 꿀을 걷습니다
beehives.next(); // hive2에서 꿀을 걷습니다
beehives.next(); // hive3에서 꿀을 걷습니다
beehives.done(); // false
beehives.next(); // hive4에서 꿀을 걷습니다
beehives.next(); // hive5에서 꿀을 걷습니다
beehives.next(); // hive6에서 꿀을 걷습니다
beehives.next(); // hive7에서 꿀을 걷습니다
beehives.done(); // false
beehives.next(); // hive8에서 꿀을 걷습니다
beehives.next(); // hive9에서 꿀을 걷습니다
beehives.done(); // true

while (!beehives.done()) {
    beehives.next();
}




/*

// 다양한 자료형의 값이 저장된 배열
const items = [1, "String", false, 1.24, {name: "lee", age: 24}];
function Iterator(items) {
    this.items = items;
    this.index = 0
}

Iterator.prototype = {
    // index 값이 배열의 길이보다 클 경우 더 이상 순회할 수 있는 값이 없다.
    hasNext: function(){
        return this.index < this.items.length;
    },
    // next 메소드를 한번 실행할 때마다 해당 index의 값을 반환하고, index의 값은 1 증가한다.
    next: function(){
        return this.items[this.index++]
    }
}

const iter = new Iterator(items)

// 조건문이 false가 될 때까지 계속적으로 순회한다.
while(iter.hasNext()){
    console.log(iter.next())
}
console.log(iter.hasNext()) // => false
*/
