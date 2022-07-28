import defaultSettings from '@/settings'

const title = defaultSettings.title || 'OES在线教育系统'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
