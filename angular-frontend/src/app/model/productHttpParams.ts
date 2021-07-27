export class ProductHttpParams {

  constructor(public titleFilter: string | null = null,
              public minCostFilter: string | null = null,
              public maxCostFilter: string | null = null,
              public page: number = 1,
              public size: number | null = null,
              public sort: string | null = null,
              public direction: string = 'asc') {
  }
}
