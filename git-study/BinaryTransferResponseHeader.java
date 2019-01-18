
public interface BinaryTransferResponseHeader {


  @Nonnull
  ResponseBuilder applyTo(@Nonnull ResponseBuilder builder);

  @Nonnull
  default ResponseModifier convert() {
    return builder -> header.applyTo(builder).build();
  }

  @Nonnull
  default ResponseModifier compose(@Nonnull ResponseModifier responseModifier) {
    return builder -> responseModifier.modify(applyTo(builder));
  }
}
