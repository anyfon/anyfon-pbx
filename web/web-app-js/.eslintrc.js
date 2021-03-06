module.exports = {
    root: true,
    parserOptions: {
        parser: '@babel/eslint-parser',
        sourceType: 'module'
    },
    env: {
        browser: true
    },
    extends: [
        'plugin:vue/vue3-essential',
        'standard',
        '@vue/typescript/recommended',
        'plugin:import/recommended',
        'plugin:import/typescript'
    ],
    plugins: [
        'vue',
        '@typescript-eslint'
    ],
    globals: {
        cordova: true,
        __statics: true,
        __QUASAR_VERSION__: true,
        __QUASAR_SSR__: true,
        __QUASAR_SSR_SERVER__: true,
        __QUASAR_SSR_CLIENT__: true,
        __QUASAR_SSR_PWA__: true
    },
    // add your custom rules here
    rules: {
        'brace-style': [ 2, 'stroustrup', { allowSingleLine: true } ],
        'prefer-const': 2,
        'prefer-promise-reject-errors': 'off',
        'multiline-ternary': 'off',
        'no-prototype-builtins': 'off',
        'no-case-declarations': 'off',
        'generator-star-spacing': 'off',
        'arrow-parens': 'off',
        'object-property-newline': 'off',
        'one-var': 'off',
        'no-void': 'off',
        'no-lone-blocks': 'error',
        'no-unused-expressions': 'error',
        'no-useless-concat': 'error',
        'no-useless-return': 'error',
        'no-unneeded-ternary': 'error',
        'no-confusing-arrow': [ 'error', { allowParens: true } ],
        'operator-linebreak': [ 'error', 'before' ],
        'no-debugger': process.env.NODE_ENV === 'production' ? 2 : 0,

        'array-bracket-spacing': [ 'error', 'always' ],
        'object-curly-spacing': [ 'error', 'always' ],
        'computed-property-spacing': [ 'error', 'always' ],
        // 'template-curly-spacing': [ 'error', 'always' ],
        'space-before-function-paren': [ 'error', 'never' ],

        'import/first': 0,
        //  'import/named': 2,
        'import/namespace': 2,
        'import/default': 2,
        'import/export': 2,
        'import/extensions': 0,
        'import/no-unresolved': 0,
        'import/no-extraneous-dependencies': 'off',

        'vue/max-attributes-per-line': 'off',
        'vue/valid-v-for': 'off',
        'vue/require-default-prop': 'off',
        'vue/require-prop-types': 'off',
        'vue/require-v-for-key': 'off',
        'vue/return-in-computed-property': 'off',
        'vue/require-render-return': 'off',
        'vue/singleline-html-element-content-newline': 'off',
        'vue/no-side-effects-in-computed-properties': 'off',
        'vue/array-bracket-spacing': 'off',
        'vue/object-curly-spacing': 'off',
        'vue/script-indent': 'off',
        'vue/no-v-model-argument': 'off',
        'vue/require-explicit-emits': 'off',

        // My
        indent: [ 'error', 4 ],
        'vue/html-indent': [ 'error', 4 ],
        'space-in-parens': [ 'error', 'always' ],
        '@typescript-eslint/no-unused-vars': 0,
        'import/no-named-as-default': 0,
        'template-curly-spacing': 0,
        'import/named': 0,
        'eol-last': 0,
        '@typescript-eslint/no-empty-interface': 'off'
    }
}
