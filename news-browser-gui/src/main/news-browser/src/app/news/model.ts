export interface NewsModel {
    country: string;
    category: string;
    articles: ArticleModel[];
}

export interface ArticleModel {
    author: string;
    title: string;
    description: string;
    date: string;
    sourceName: string;
    articleUrl: string;
    imageUrl: string;
}

export interface PaginationParams {
    totalItemsNo: number;
    itemPerPageNo: number;
    totalPagesNo: number;
    currentPageNo: number;
    dataToBeDisplayByPageNo: {};
}