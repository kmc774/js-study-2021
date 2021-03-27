let arr = [8,15,6,9,1658,1668,12,48,62,1];
index = 0;
even = 0;
odd = 0;

for(let count=0; count<=arr.length -1; count++){
    if( arr[count]%2 === 0){
        even++;
    }else{
        odd++;
    }
}

