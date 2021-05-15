let star = '';
for (let i = 0; i < 20; i++) {
  if (i < 10)
    for (let j = 0; j <= i; j++) {
      star += '*';
    }
  else {
    for (let j = 19; j > i; j--) {
      star += '*';
    }
  }
  star += '\n';
}
console.log(star);
