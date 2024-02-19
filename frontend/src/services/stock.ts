import { CreateStockType, PatchStockType, StockType } from "../interfaces";
import { Fetch } from "../utils";

class StockService {
  async createStock(dataStock: CreateStockType): Promise<StockType> {
    const { data: stock }: { data: StockType } = await Fetch.post<StockType>(
      "/stock",
      dataStock,
    );

    return stock;
  }

  async getStock(id: string): Promise<StockType> {
    const { data: stock }: { data: StockType } = await Fetch.get<StockType>(
      `/stock/${id}`,
    );

    return stock;
  }

  async getAllStock(): Promise<StockType[]> {
    const { data: stocks }: { data: StockType[] } =
      await Fetch.get<StockType[]>("/stock");

    return stocks;
  }

  async patchStock(id: string, patchData: PatchStockType): Promise<number> {
    const { data: objectModified }: { data: number } =
      await Fetch.patch<number>(`/stock/${id}`, patchData);

    return objectModified;
  }

  async deleteStock(id: string): Promise<string> {
    const { data: textInfo }: { data: string } = await Fetch.delete<string>(
      `/stock/${id}`,
    );

    return textInfo;
  }
}

export const stockService: StockService = new StockService();
