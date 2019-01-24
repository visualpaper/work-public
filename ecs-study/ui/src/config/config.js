const Config = () => {
  if (process.env.NODE_ENV === 'test') {
    // test
    return {
      url: process.env.REACT_APP_TEST_API_URL
    }
  } else if (process.env.NODE_ENV === 'production') {
    // production
    return {
      url: process.env.REACT_APP_PROD_API_URL
    }
  }
  // development
  return {
    url: process.env.REACT_APP_DEV_API_URL
  }
}

export const AppConfig = Config()
