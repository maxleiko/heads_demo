var fr = fr || {};
if(!fr.inria) fr.inria = {};
if(!fr.inria.diverse) fr.inria.diverse = {};
if(!fr.inria.diverse.noveltytesting) fr.inria.diverse.noveltytesting = {};
if(!fr.inria.diverse.noveltytesting.samples) fr.inria.diverse.noveltytesting.samples = {};
fr.inria.diverse.noveltytesting.samples.Functions = function() { };
fr.inria.diverse.noveltytesting.samples.FunctionsImpl = function() {
};
fr.inria.diverse.noveltytesting.samples.FunctionsImpl.__interfaces__ = [fr.inria.diverse.noveltytesting.samples.Functions];
fr.inria.diverse.noveltytesting.samples.FunctionsImpl.prototype = {
	sum: function(a,b) {
		return a + b;
	}
	,inverse: function(b) {
		return !b;
	}
	,echo: function(say) {
		return say;
	}
	,concat: function(a,b) {
		return a + b;
	}
};

module.exports = fr.inria.diverse.noveltytesting.samples.FunctionsImpl;