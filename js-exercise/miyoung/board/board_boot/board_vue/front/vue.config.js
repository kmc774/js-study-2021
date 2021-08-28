module.exports = {
    devServer: {
        proxy: {
            '^/board': {
                target: 'http://localhost:8080',
                changeOrigin: true,
                secure: false,
                logLevel: 'debug'
            },
        }
    }
}