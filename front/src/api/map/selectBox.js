import { showMap } from './showMap'
import api from '@/util/axios'
import { $, createElement } from '@/util/elementTool'

export async function selectArea() {
  let areaUrl = '/regions/sido'
  const res = await api(areaUrl, 0)
  const data = await res.data
  console.log(data)
  makeArea(data)
}

export async function selectSigungu() {
  if ($('#search-area').querySelector('.selected').value !== -1) {
    let sel = $('#search-area')

    let selval = sel.querySelector('.selected').value
    let areaCode = selval
    let sigunguUrl = '/regions/gugun?sidoCode=' + areaCode

    let selSigungu = $('#search-sigungu')
    let optionsContainer = selSigungu.querySelector('.options-container')
    optionsContainer.innerHTML = ''

    const res = await api(sigunguUrl, 0)
    const data = await res.data

    data.forEach((sigungu) => {
      let opt = createElement('div')
      opt.className = 'option'
      let inpt = createElement('input')
      inpt.type = 'radio'
      inpt.className = 'radio'
      inpt.name = sigungu.gugun_name
      inpt.value = sigungu.gugun_code
      let lnpt = createElement('label', sigungu.gugun_name)

      opt.appendChild(inpt)
      opt.appendChild(lnpt)
      optionsContainer.appendChild(opt)
    })
    selectFor('#search-sigungu')
  }
}

export function selectContent() {
  selectFor('#search-content-id')
}

export function searchFin() {
  selectForSearch()
}

function makeArea(data) {
  if ($('#search-sigungu').querySelector('.selected').value !== -1) {
    $('#search-sigungu').querySelector('.selected').value = -1
    $('#search-sigungu').querySelector('.selected').innerHTML = '시/군/구'
  }
  let sel = $('#search-area')
  let optionsContainer = sel.querySelector('.options-container')
  optionsContainer.innerHTML = ''

  data.forEach(function (area) {
    let opt = createElement('div')
    opt.className = 'option'
    let inpt = createElement('input')
    inpt.type = 'radio'
    inpt.className = 'radio'
    inpt.name = area.sido_code
    inpt.value = area.sido_code
    let lnpt = createElement('label', area.sido_name)
    opt.appendChild(inpt)
    opt.appendChild(lnpt)
    optionsContainer.appendChild(opt)
  })
  selectFor('#search-area')
}

export const selectFor = (query) => {
  const contentId = $(query)
  const selected = contentId.querySelector('.selected')
  const optionsContainer = contentId.querySelector('.options-container')
  const optionsList = optionsContainer.querySelectorAll('.option')
  optionsContainer.classList.toggle('active')

  optionsList.forEach((o) => {
    o.addEventListener('click', () => {
      const optionsContainer = contentId.querySelector('.options-container')

      selected.innerHTML = o.querySelector('label').innerHTML
      selected.value = o.querySelector('input').value
      optionsContainer.classList.remove('active')
    })
  })
}

const selectForSearch = () => {
  const searchAreaAll = [-1, -1, -1]
  searchAreaAll[0] = $('#search-area').querySelector('.selected').value
  searchAreaAll[1] = $('#search-sigungu').querySelector('.selected').value
  searchAreaAll[2] = $('#search-content-id').querySelector('.selected').value
  console.log(searchAreaAll)
  showMap(searchAreaAll)
}
