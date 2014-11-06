var AbstractComponent = require('kevoree-entities').AbstractComponent;
var FunctionsImpl = require('./FunctionsImpl');

/**
 * Kevoree component
 * @type {FunctionsImpl}
 */
var FunctionsImplComp = AbstractComponent.extend({
    toString: 'FunctionsImpl',

    construct: function () {
        this.func = new FunctionsImpl();
    },

    in_inverse: function () {
        return this.func.inverse.apply(this.func, arguments);
    },

    in_echo: function () {
        return this.func.echo.apply(this.func, arguments);
    },

    in_sum: function () {
        return this.func.sum.apply(this.func, arguments);
    },

    in_concat: function () {
        return this.func.concat.apply(this.func, arguments);
    }
});

module.exports = FunctionsImplComp;
