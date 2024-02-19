import { useState } from "react";

import { SaleCard } from "./sale.style";
import { MultiSubContainer } from "../../../global/global.style";
import { KontinumP } from "../../title/title.style";

export function Sale() {
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const [sale, setSale] = useState([
    {
      price: "$54.00",
      name: "Steven summers",
      time: "02 minutes ago",
    },
    {
      price: "$20.00",
      name: "Steven summers",
      time: "02 minutes ago",
    },
    {
      price: "$32.00",
      name: "Steven summers",
      time: "02 minutes ago",
    },
  ]);

  return (
    <MultiSubContainer
      justifyContent={"center"}
      alignItems={"center"}
      gap={"10px"}
    >
      {sale.map((s) => {
        return (
          <SaleCard>
            <MultiSubContainer margin={"20px"} gap={"20px"}>
              <KontinumP color={"#ffffff"}>{s.name}</KontinumP>
              <KontinumP color={"#ffffff"} opacity={0.4} fs={"14px"}>
                {s.time}
              </KontinumP>
            </MultiSubContainer>
            <MultiSubContainer
              justifyContent={"center"}
              alignItems={"center"}
              style={{ height: "100%" }}
            >
              <KontinumP color={"#ffffff"}>+ {s.price}</KontinumP>
            </MultiSubContainer>
          </SaleCard>
        );
      })}
    </MultiSubContainer>
  );
}
