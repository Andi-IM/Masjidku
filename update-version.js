const fs = require('fs');
const version = process.argv[2];
if (!version) {
  console.error("No version provided");
  process.exit(1);
}
let content = fs.readFileSync('build.gradle', 'utf8');
content = content.replace(/version\s*=\s*['"].*?['"]/, `version = '${version}'`);
fs.writeFileSync('build.gradle', content);
console.log(`Updated build.gradle version to ${version}`);
