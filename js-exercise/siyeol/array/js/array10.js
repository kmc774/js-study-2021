let arr = Array.from(Array(3), () => new Array(3)); //es6 이상부터 사용 가능한 배열 생성 방법
let columns = 0;
let rows = 0;

//  2차원 배열 값 채우기
for( let count=0; count<=8; count++ ){
    arr[columns][rows] = count;
    rows ++;
    if( (count+1)%3 === 0 ){
        columns ++;
        rows = 0;
    }
}

let lineSum = [
    //가로
    arr[0][0]+arr[0][1]+arr[0][2],
    arr[1][0]+arr[1][1]+arr[1][2],
    arr[2][0]+arr[2][1]+arr[2][2],
    //세로
    arr[0][0]+arr[1][0]+arr[2][0],
    arr[0][1]+arr[1][1]+arr[2][1],
    arr[0][2]+arr[1][2]+arr[2][2],
    //대각선
    arr[0][0]+arr[1][1]+arr[2][2],
    arr[0][2]+arr[1][1]+arr[2][0]
];

