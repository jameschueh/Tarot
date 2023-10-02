let proxyObj = {}

proxyObj['/'] = {
  ws: false,
  target: 'http://localhost:8888',
  changeOrigin: true,
  pathWrite: {
    '^/': '/'
  }
}

module.exports = {
  devServer: {
    host: 'localhost',
    port: 8080,
    Proxy: proxyObj
  }
}
