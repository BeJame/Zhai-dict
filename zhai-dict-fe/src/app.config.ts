export default {
  pages: [
    'pages/home/home',
    'pages/settings/settings',
    'pages/books/books',
    'pages/me/me',
    'pages/spell/spell',
    'pages/about/about',
    'pages/history/history',
    'pages/collection/collection',
  ],
  window: {
    backgroundTextStyle: 'light',
    navigationBarBackgroundColor: '#fff',
    navigationBarTitleText: 'WeChat',
    navigationBarTextStyle: 'black'
  },
  "tabBar": {
    "color": "#999",
    "backgroundColor": "#fafafa",
    "selectedColor": "#333",
    "borderStyle": "black",
    "list": [
      {
        "text": "首页",
        "pagePath": "pages/home/home",
        "iconPath": '/_/assets/tabs/icon_circle_line.png',
        "selectedIconPath": '/_/assets/tabs/icon_circle_line1.png'
      },
      {
        "text": "我的",
        "pagePath": "pages/me/me",
        "iconPath": '/_/assets/tabs/icon_patriarch.png',
        "selectedIconPath": '/_/assets/tabs/icon_patriarch1.png'
      }
    ],
    "position": "bottom"
  }
}
