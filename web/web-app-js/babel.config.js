module.exports = {
    env: {
        development: {
            presets: [
                '@quasar/babel-preset-app',
                '@babel/preset-typescript'
            ]
        },

        production: {
            presets: [ 'es2015-rollup' ],
            comments: false
        }
    }
}
