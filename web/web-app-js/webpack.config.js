require( 'dotenv' ).config( { path: './.env' } )

const path = require( 'path' )
const MiniCssExtractPlugin = require( 'mini-css-extract-plugin' )
const { VueLoaderPlugin } = require( 'vue-loader' )
const HtmlWebpackPlugin = require( 'html-webpack-plugin' )
const webpack = require( 'webpack' )

const basePath = path.resolve( __dirname )

const nodeModulesPath = path.resolve( basePath, 'node_modules' )

const mainPath = path.resolve( basePath, 'src/main' )

const componentPath = path.resolve( mainPath, 'component' )
const layoutPath = path.resolve( mainPath, 'layout' )
const pagePath = path.resolve( mainPath, 'page' )
const viewPath = path.resolve( mainPath, 'view' )

const stylePath = path.resolve( basePath, 'src/style' )

const distPath = path.resolve( basePath, '../../build/platform-app-js/dist' )

const devConfig = {
    mode: 'development',
    devServer: {
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, PATCH, OPTIONS',
            'Access-Control-Allow-Headers': 'X-Requested-With, content-type, Authorization'
        },
        // contentBase: path.join( __dirname, 'dist' ),
        compress: true,
        host: '0.0.0.0',
        port: process.env.DEV_PORT,
        allowedHosts: [
            'localhost'
        ],
        proxy: {
            '/api/**': 'http://' + process.env.DEV_BACKEND_HOST + ':' + process.env.DEV_BACKEND_PORT
        }
    }
}

const prodConfig = {
    mode: 'production',
    output: {
        publicPath: '/static/app/',
        path: distPath
    }
}

module.exports = ( env, argv ) => {
    const config = {}
    const isProd = argv.mode === 'production'

    config.output = {
        publicPath: 'http://' + process.env.DEV_HOST + ':' + process.env.DEV_PORT + '/'
    }

    config.entry = {
        app: path.resolve( mainPath, 'index.ts' )
    }

    config.resolve = {
        modules: [ nodeModulesPath, mainPath, stylePath ],
        extensions: [ '.scss', '.css', '.vue', '.sass', '.ts', '.tsx', '.js' ],
        alias: {
            '@main': mainPath,
            '@style': stylePath,
            '@component': componentPath,
            '@layout': layoutPath,
            '@page': pagePath,
            '@view': viewPath
        }
    }

    config.module = {
        rules: [
            {
                test: /\.(ts|tsx)$/,
                loader: 'ts-loader',
                options: {
                    appendTsSuffixTo: [ /\.vue$/ ]
                },
                exclude: /node_modules/
            },
            {
                test: /\.js$/,
                exclude: file => (
                    /node_modules/.test( file )
                    && !/\.vue\.js/.test( file )
                ),
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: [ '@babel/preset-env' ]
                    }
                }
            },
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },
            {
                test: /\.(css|sass|scss)$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                    {
                        loader: 'sass-loader',
                        options: {
                            sassOptions: {
                                indentedSyntax: true
                            }
                        }
                    }
                ]
            },
            {
                test: /\.(eot|svg|ttf|woff|woff2)$/,
                type: 'asset/resource'
            }
        ]
    }

    config.plugins = [
        new VueLoaderPlugin(),
        new MiniCssExtractPlugin( {
            filename: '[name].css',
            chunkFilename: '[id].css'
        } ),
        new webpack.DefinePlugin( {
            __QUASAR_SSR__: undefined,

            __QUASAR_SSR_PWA__: undefined,
            __QUASAR_SSR_CLIENT__: undefined,
            __QUASAR_SSR_SERVER__: undefined,
            __QUASAR_VERSION__: '2',

            'process.env': JSON.stringify( process.env )
        } )
    ]

    if ( isProd ) {
        Object.assign( config, prodConfig )
    }
    else {
        config.plugins.push( new HtmlWebpackPlugin( {
            templateContent: '<div id="desktop-web-app"></div>',
            favicon: './asset/favicon.ico'
        } ) )
        Object.assign( config, devConfig )
    }

    return config
}
