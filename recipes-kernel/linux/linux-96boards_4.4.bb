require linux.inc

DESCRIPTION = "Generic 96boards kernel"

PV = "4.4.8+git"
SRCREV_kernel = "512284bf605ae63d7f54777a8fc63c2735aa90e4"
SRCREV_FORMAT = "kernel"

SRC_URI = "git://github.com/96boards/linux.git;protocol=https;branch=96b/releases/2016.06;name=kernel \
          "
S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "96boards-64|96boards-32|juno|hikey"
KERNEL_IMAGETYPE ?= "Image"
# make[3]: *** [scripts/extract-cert] Error 1
DEPENDS += "openssl-native"
HOST_EXTRACFLAGS += "-I${STAGING_INCDIR_NATIVE}"

do_configure() {
    cp ${S}/arch/arm64/configs/distro.config ${B}/.config
    yes '' | oe_runmake -C ${S} O=${B} oldconfig
}
