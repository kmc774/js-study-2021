let arr = Array.from(Array(3), () => new Array(3)); //es6 이상부터 사용 가능한 배열 생성 방법
let columns = 0;
let rows = 0;
let result = '';

for( let count=0; count<=8; count++ ){
    arr[columns][rows] = count;
    rows ++;
    if( (count+1)%3 === 0 ){
        columns ++;
        rows = 0;
    }
}

for( let c=0; c<arr.length; c++ ){
    for( let r=0; r<arr[c].length; r++ ){
        result = result + arr[c][r] + " ";
    }
    result += '<br/>';
}
document.body.innerHTML = result;
