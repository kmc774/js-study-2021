let arr = new Array();
index = 0;

for(let count=0; count<=100; count++){
    if( index%2 === 0){
        arr[count] = 0;
    }
}

for(let count=0; count<=100; count++){
    if( index%2 === 0){
        arr[index] = 1;
    }
    index++ ;
}

