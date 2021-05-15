let star = '';
for (let i = 0; i < 10; i++) {
  for (let j = 0; j <= i; j++) {
    star += '*';
  }
  star += '\n';
}
console.log(star);
