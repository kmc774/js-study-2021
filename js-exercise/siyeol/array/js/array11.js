let arr = new Array;
let sum1 = 0;
let sum2 = 0;
let sum3 = 0;
let lineSum = [];

//  2차원 배열 값 채우기
for( let count=0; count<=8; count++ ){
    arr[count] = count;
}

//------ 가로라인의 합 --------
for( let count=0; count<9; count++ ){
    if( count < 3 ){
        sum1 += arr[count];
    }
    if( count>=3 && count<6 ){
        sum2 += arr[count];
    }
    if( count>=6 ){
        sum3 += arr[count];
    }
}
lineSum.push(sum1, sum2, sum3);
sum1 = 0; sum2= 0; sum3 = 0;

//------ 세로라인의 합 --------
for( let count=0; count<9; count++ ){
    if( count%3 === 0 ){
        sum1 += arr[count];
    }
    if( count%3 === 1 ){
        sum2 += arr[count];
    }
    if( count%3 === 2 ){
        sum3 += arr[count];
    }
}
lineSum.push(sum1, sum2, sum3);
sum1 = 0; sum2= 0; sum3 = 0;

//------ 대각라인의 합 --------
for( let count=0; count<9; count++ ){
    if( count%4 === 0 ){
        sum1 += arr[count];
    }
    if( count%2 === 0 && count != arr.slice(0,1) && count != arr.slice(-1) ){
        sum2 += arr[count];
    }
}
lineSum.push(sum1, sum2);



/*
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
*/
