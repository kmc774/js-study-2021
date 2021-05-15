let star = '';
for (let i = 0; i < 4; i++) {
  if (i % 2 == 0) {
    for (let j = 0; j < 7; j++) {
      if (j % 2 == 0) {
        star += '*';
      } else star += ' ';
    }
  } else {
    for (let j = 0; j < 7; j++) {
      if (j % 2 == 0) {
        star += ' ';
      } else star += '*';
    }
  }
  star += '\n';
}
console.log(star);
