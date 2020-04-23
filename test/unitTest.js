// using nodejs's build in asserts that throw on failure
var assert = require('assert')
 
exports['test that stops execution on first failure'] = function() {
  assert.equal(3 + 2, 5, 'will never pass this since test failed above')
}
 
if (module == require.main) require('test').run(exports)