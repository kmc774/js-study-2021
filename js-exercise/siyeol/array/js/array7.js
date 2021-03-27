let arr = new Array();
let resultArr = new Array();
let valueArr = new Array();

for( let count=1; count<=100; count++ ){
    arr[count-1] = count;
}

for( let count=0; count<arr.length; count++ ){

    valueArr.push(arr[count]);

    if( arr[count]%10 === 0 && count != 0 ){
        //참조문제 때문에 valueArr배열에 마지막에 들어간 값들로 resultArr배열 값이 채워진다
        let stmpArr = new Array();

        for( let valueArrIdx=0; valueArrIdx<valueArr.length; valueArrIdx++ ){
            stmpArr.push(valueArr[valueArrIdx]);
        }
        
        resultArr.push(stmpArr);
        valueArr = [];
    }
    
}
/*
stampArr는 for문이 1번째 for문이 돌 때 마다 새로 생성된다.
각각 생성된 stmpArr는 서로 다른 주소에 저장되어 있다.
그렇기 때문에 valueArr를 resultArr에 바로 넣었을 때 발생했던 참조 문제가 발생하지 않는다.
(21라인에서 valueArr 초기화 해주면 resultArr에 담긴 모든 배열의 값에는 초기화된 valueArr를 나타낸다.) 
*/
